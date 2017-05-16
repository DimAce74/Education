package ru.udmonline;

import java.io.File;

public interface ICamundaPT {

	void init(File schema, boolean historyOn, String jdbc) throws Exception;

	void startAndFinishProcess() throws Exception;
}
