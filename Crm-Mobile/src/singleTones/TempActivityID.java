package singleTones;

public class TempActivityID {

		private static TempActivityID mInstance = null;

		private String ActivityID;

		private TempActivityID(){
			ActivityID = new String();
		}

		public static TempActivityID getInstance(){
			if(mInstance == null)
			{
				mInstance = new TempActivityID();
			}
			return mInstance;
		}

		public String getTempActivityID() {
			return ActivityID;
		}

		public void setTempActivityID(String activityID) {
			ActivityID = activityID;
		}

	}

