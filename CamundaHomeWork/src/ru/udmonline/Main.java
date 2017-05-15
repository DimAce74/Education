package ru.udmonline;

import org.camunda.bpm.engine.*;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.bpm.model.bpmn.Bpmn;
import org.camunda.bpm.model.bpmn.BpmnModelInstance;

import java.io.File;

public class Main {

    private static ProcessEngine processEngine;
    private static RepositoryService repositoryService;
    private static RuntimeService runtimeService;
    private static TaskService taskService;
    private static BpmnModelInstance modelInstance;
    private static String pathToBpmn;

    public static void main(String[] args) {
        pathToBpmn = args[1];
        configure(args[0]);

    }

    private static void configure(String isJournalizing) {
        ProcessEngineConfiguration engineConfiguration = ProcessEngineConfiguration.createStandaloneProcessEngineConfiguration()
                .setDatabaseSchemaUpdate(ProcessEngineConfiguration.DB_SCHEMA_UPDATE_TRUE)
                .setJdbcDriver("org.postgresql.Driver")
                .setJdbcUrl("jdbc:postgresql://localhost:5432/camunda")
                .setJdbcUsername("postgres")
                .setJdbcPassword("db2admin");
        switch (isJournalizing) {
            case "yes":
                processEngine = engineConfiguration.setHistory(ProcessEngineConfiguration.HISTORY_FULL).buildProcessEngine();
                break;
            case "no":
                processEngine = engineConfiguration.setHistory(ProcessEngineConfiguration.HISTORY_NONE).buildProcessEngine();
                break;
            default:
                System.out.println("Неверный первый параметр");
                System.exit(0);
        }
        repositoryService = processEngine.getRepositoryService();
        runtimeService = processEngine.getRuntimeService();
        taskService = processEngine.getTaskService();
        modelInstance = Bpmn.readModelFromFile(new File(pathToBpmn));
        repositoryService.createDeployment().addModelInstance("test_PT", modelInstance).deploy();
        repositoryService.activateProcessDefinitionById("Process_1");
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("Process_1");
    }
}
