package ru.udmonline;

import java.io.File;

public class Main {

    public static void main(String[] args) throws Exception {
        String pathToBpmn = args[1];
        File schema = new File(pathToBpmn);
        boolean historyOn = Boolean.parseBoolean(args[0]);
        String jdbc = "jdbc:postgresql://localhost:5432/camunda_1";

        ICamundaPT iCamundaPT = new CamundaPT();
        iCamundaPT.init(schema, historyOn, jdbc);
        iCamundaPT.startAndFinishProcess();
    }
}
