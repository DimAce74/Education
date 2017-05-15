package ru.udmonline;

import org.camunda.bpm.engine.*;
import org.camunda.bpm.engine.runtime.ActivityInstance;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.bpm.model.bpmn.Bpmn;
import org.camunda.bpm.model.bpmn.BpmnModelInstance;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;

public class Main {

    private static final String PATH_TO_BPMN_MODEL = "C:/repo/Education/camunda-sample/resources/test_PT.bpmn";

    public static void main(String[] args) {
        System.out.println("Нужно журналирование? 1)Да, 2)Нет");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int choice=2;
       /*
        try {
            choice = Integer.parseInt(reader.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        }
        */
        ProcessEngine processEngine = getProcessEngine(choice);
        BpmnModelInstance modelInstance = getModelInstance(PATH_TO_BPMN_MODEL);
        RepositoryService repositoryService = processEngine.getRepositoryService();
        repositoryService.activateProcessDefinitionById("test_process");
        repositoryService.createDeployment().addModelInstance("test_PT.bpmn", modelInstance).deploy();
        RuntimeService runtimeService = processEngine.getRuntimeService();
        System.out.println(repositoryService.createProcessDefinitionQuery().list());

        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("test_process");
        ActivityInstance activityInstance = runtimeService.getActivityInstance(processInstance.getProcessInstanceId());
        System.out.println(activityInstance);
        ManagementService managementService = processEngine.getManagementService();
        managementService.
        /*
        Collection<FlowNode> flowNodes = modelInstance.getModelElementsByType(FlowNode.class);
        for (FlowNode flowNode : flowNodes) {
            System.out.println(flowNode.getId());

        }
        Activity task = modelInstance.getModelElementById("Task_0kpcca1");
        System.out.println(task.getName());
*/
    }

    private static ProcessEngine getProcessEngine(int choice) {
        ProcessEngineConfiguration configuration = ProcessEngineConfiguration.createStandaloneProcessEngineConfiguration()
                .setDatabaseSchemaUpdate(ProcessEngineConfiguration.DB_SCHEMA_UPDATE_TRUE)
                .setJdbcUrl("jdbc:postgresql://localhost:5432/camunda")
                .setJdbcUsername("postgres")
                .setJdbcPassword("db2admin")
                .setJdbcDriver("org.postgresql.Driver");
        if (choice == 1) {
            configuration.setHistory(ProcessEngineConfiguration.HISTORY_FULL);
        }else{
            configuration.setHistory(ProcessEngineConfiguration.HISTORY_NONE);
        }
        return configuration.buildProcessEngine();
    }

    private static BpmnModelInstance getModelInstance (String pathToBpmnModel){
        return Bpmn.readModelFromFile(new File(pathToBpmnModel));
    }
}
