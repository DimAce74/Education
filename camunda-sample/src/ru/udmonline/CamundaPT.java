package ru.udmonline;

import org.camunda.bpm.engine.*;
import org.camunda.bpm.engine.repository.ProcessDefinition;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.bpm.engine.task.Task;
import org.camunda.bpm.model.bpmn.Bpmn;
import org.camunda.bpm.model.bpmn.BpmnModelInstance;

import java.io.File;
import java.util.List;

public class CamundaPT implements ICamundaPT {

    private ProcessEngine processEngine;
    private RuntimeService runtimeService;
    private TaskService taskService;

    private void getProcessEngine(boolean historyOn, String jdbc) {

        ProcessEngineConfiguration configuration = ProcessEngineConfiguration.createStandaloneProcessEngineConfiguration()
                .setDatabaseSchemaUpdate(ProcessEngineConfiguration.DB_SCHEMA_UPDATE_CREATE_DROP)
                .setJdbcUrl(jdbc)
                .setJdbcUsername("postgres")
                .setJdbcPassword("db2admin")
                .setJdbcDriver("org.postgresql.Driver");
        /*
        DataSource dataSource = configuration.getDataSource();
        TransactionFactory transactionFactory = new JdbcTransactionFactory();
        Environment environment = new Environment("development", transactionFactory, dataSource);
        Configuration config = new Configuration(environment);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(config);
        */
        if (historyOn) {
            //try {
                //SqlSession session = sqlSessionFactory.openSession();
                //session.getConnection().prepareStatement("SET camunda.public.act_ge_property.historyLevel = 3").execute();
                //session.commit();
            //} catch (SQLException e) {
                //e.printStackTrace();
            //}
            configuration.setHistory(ProcessEngineConfiguration.HISTORY_FULL);
        }else{
            /*
            try {
                SqlSession session = sqlSessionFactory.openSession();
                session.getConnection().prepareStatement("SET camunda.public.act_ge_property.historyLevel = 0").execute();
                session.commit();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            */
            configuration.setHistory(ProcessEngineConfiguration.HISTORY_NONE);
        }
        processEngine = configuration.buildProcessEngine();
    }

	@Override
	public void init(File schema, boolean historyOn, String jdbc )
			throws Exception {
        getProcessEngine(historyOn, jdbc);
        BpmnModelInstance modelInstance = Bpmn.readModelFromFile(schema);
        RepositoryService repositoryService = processEngine.getRepositoryService();
        List<ProcessDefinition> processDefinitions = repositoryService.createProcessDefinitionQuery().list();
        if (processDefinitions.isEmpty()){
            repositoryService.createDeployment().addModelInstance("test_PT.bpmn", modelInstance).deploy();
        }
        runtimeService = processEngine.getRuntimeService();
        taskService = processEngine.getTaskService();
    }

	@Override
	public void startAndFinishProcess() throws Exception {
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("test_process");
        List<Task> activeTasks;
        while (true){
            activeTasks = taskService.createTaskQuery().active().processInstanceId(processInstance.getProcessInstanceId()).list();
            if (!activeTasks.isEmpty()) {
                for (Task activeTask : activeTasks) {
                    System.out.println("Этап " + activeTask.getName() + " начат");
                    if (activeTask.getName().equals("Stage3")) {
                        taskService.setVariable(activeTask.getId(), "var1", "2");
                    }
                    taskService.complete(activeTask.getId());
                    System.out.println("Этап " + activeTask.getName() + " завершен");
                }
            }else{
                break;
            }
        }
        System.out.println("Все этапы завершены!");
    }
}

