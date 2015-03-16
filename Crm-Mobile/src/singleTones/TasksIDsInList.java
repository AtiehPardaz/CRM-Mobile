package singleTones;

public class TasksIDsInList {

		private static TasksIDsInList mInstance = null;

		private String[][] TaskID;

		private TasksIDsInList(){
			TaskID = new String[24][30];
		}

		public static TasksIDsInList getInstance(){
			if(mInstance == null)
			{
				mInstance = new TasksIDsInList();
			}
			return mInstance;
		}

		public String[][] getTaskID() {
			return TaskID;
		}

		public void setSalt(String[][] taskID) {
			TaskID = taskID;
		}

	}

