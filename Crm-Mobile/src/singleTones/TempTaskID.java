package singleTones;

public class TempTaskID {

		private static TempTaskID mInstance = null;

		private String TaskID;

		private TempTaskID(){
			TaskID = new String();
		}

		public static TempTaskID getInstance(){
			if(mInstance == null)
			{
				mInstance = new TempTaskID();
			}
			return mInstance;
		}

		public String getTempTaskID() {
			return TaskID;
		}

		public void setTempTaskID(String taskID) {
			TaskID = taskID;
		}

	}

