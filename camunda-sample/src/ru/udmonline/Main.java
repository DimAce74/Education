package ru.udmonline;

import org.camunda.bpm.engine.*;
import org.camunda.bpm.engine.repository.ProcessDefinition;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.bpm.engine.task.Task;
import org.camunda.bpm.model.bpmn.Bpmn;
import org.camunda.bpm.model.bpmn.BpmnModelInstance;

import java.io.File;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        ProcessEngine processEngine = getProcessEngine(args[0]);
        String pathToBpmn = args[1];
        BpmnModelInstance modelInstance = getModelInstance(pathToBpmn);
        RepositoryService repositoryService = processEngine.getRepositoryService();
        List<ProcessDefinition> processDefinitions = repositoryService.createProcessDefinitionQuery().list();
        if (processDefinitions.isEmpty()){
            repositoryService.createDeployment().addModelInstance("test_PT.bpmn", modelInstance).deploy();
        }
        RuntimeService runtimeService = processEngine.getRuntimeService();
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("test_process");
        TaskService taskService = processEngine.getTaskService();
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

    private static ProcessEngine getProcessEngine(String arg) {
        ProcessEngineConfiguration configuration = ProcessEngineConfiguration.createStandaloneProcessEngineConfiguration()
                .setDatabaseSchemaUpdate(ProcessEngineConfiguration.DB_SCHEMA_UPDATE_TRUE)
                .setJdbcUrl("jdbc:postgresql://localhost:5432/camunda_1")
                .setJdbcUsername("postgres")
                .setJdbcPassword("db2admin")
                .setJdbcDriver("org.postgresql.Driver");
        if (arg.equals("yes")) {
            configuration.setHistory(ProcessEngineConfiguration.HISTORY_FULL);
        }else if (arg.equals("no")){
            configuration.setHistory(ProcessEngineConfiguration.HISTORY_NONE);
        }else{
            System.out.println("Неверный первый аргумент");
            System.exit(0);
        }
        return configuration.buildProcessEngine();
    }

    private static BpmnModelInstance getModelInstance (String pathToBpmnModel){
        return Bpmn.readModelFromFile(new File(pathToBpmnModel));
    }
}
