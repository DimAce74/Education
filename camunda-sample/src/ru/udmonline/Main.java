package ru.udmonline;

import org.camunda.bpm.engine.*;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.bpm.engine.task.Task;
import org.camunda.bpm.model.bpmn.Bpmn;
import org.camunda.bpm.model.bpmn.BpmnModelInstance;

import java.io.File;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        ProcessEngine processEngine = getProcessEngine(args[0]);
        String parhToBpmn = args[1];
        BpmnModelInstance modelInstance = getModelInstance(parhToBpmn);
        RepositoryService repositoryService = processEngine.getRepositoryService();
        repositoryService.activateProcessDefinitionById("test_process");
        repositoryService.createDeployment().addModelInstance("test_PT.bpmn", modelInstance).deploy();
        RuntimeService runtimeService = processEngine.getRuntimeService();
        System.out.println(repositoryService.createProcessDefinitionQuery().list());

        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("test_process");
        //ActivityInstance activityInstance = runtimeService.getActivityInstance(processInstance.getProcessInstanceId());
        //System.out.println(activityInstance);

        //runtimeService.createProcessInstanceModification(processInstance.getId())
        //        .startBeforeActivity()
        //runtimeService.createProcessInstanceModification(processInstance.getId())
        //        .startBeforeActivity("ExclusiveGateway_1na8m9a")
         //       .setVariable("var1", "1")
         //       .execute();
        TaskService taskService = processEngine.getTaskService();
        List<Task> activeTasks = null;
        while (true){
            activeTasks = taskService.createTaskQuery().active().processInstanceId(processInstance.getProcessInstanceId()).list();
            System.out.println(activeTasks);
            if (!activeTasks.isEmpty()) {
                for (Task activeTask : activeTasks) {
                    System.out.println("Начало этапа " + activeTask.getName() + " id " + activeTask.getId());
                    if (activeTask.getName().equals("Stage3")) {

                        taskService.setVariable(activeTask.getId(), "var1", "2");
                        //variables.put("var1", "1");
                    }
                    System.out.println(taskService.getVariables(activeTask.getId()));

                    taskService.complete(activeTask.getId());
                    System.out.println("Этап " + activeTask.getName() + " завершен");
                }
            }else{
                break;
            }
        }


/*
        activityInstance = runtimeService.getActivityInstance(processInstance.getProcessInstanceId());
        System.out.println(activityInstance);


        Collection<FlowNode> flowNodes = modelInstance.getModelElementsByType(FlowNode.class);
        for (FlowNode flowNode : flowNodes) {
            System.out.println(flowNode.getId());

        }
        Activity task = modelInstance.getModelElementById("Task_0kpcca1");
        System.out.println(task.getName());
*/
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
