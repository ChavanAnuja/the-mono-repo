package common;

public class ApiEndPoints {
	
	
	private final static String DIRECTORY_BASE = "http://localhost:8081";
	private final static String PATIENT_BASE = "http://localhost:8082";
	private final static String APPOINTMENT_BASE = "http://localhost:8084";
	private final static String CASES_BASE = "http://localhost:8083";

	// Directory Service
	public final static String LOGIN = DIRECTORY_BASE + "/api/v1/directory/validate";
	public static final String ADD_USER=DIRECTORY_BASE + "/api/v1/directory/add";

	
	
	// Patient Service
    public static final String ADD_PATIENT = PATIENT_BASE +"/api/v1/patient/add";
    public static final String UPDATE_PATIENT = PATIENT_BASE +"/api/v1/patient/";
    public static final String DELETE_PATIENT= PATIENT_BASE+ "/api/v1/patient/";

    
    // Case Service
    public static final String ADD_CASE = CASES_BASE +"/api/v1/case/add";
    public static final String UPDATE_CASE= CASES_BASE+ "/api/v1/case/get/";
    public static final String DELETE_CASE= CASES_BASE+"/api/v1/case/";

    
    //Appointment Service
   public static final String ADD_APPOINTMENT = APPOINTMENT_BASE + "/api/v1/appointment/add";
public static final String GET_APPOINTMENT=APPOINTMENT_BASE + "/api/v1/appointment/get/";
public static final String DELETE_APPOINTMENT=APPOINTMENT_BASE + "/api/v1/appointment/";
    


	
}
