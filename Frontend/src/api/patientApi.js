// CommonFunctionOfBoth  /All
import { request } from './http.js';

/** GET /All/PatientData?healthCardNo= — patient details + all prescriptions */
export const getPatientData = healthCardNo => request('GET', '/All/PatientData', { params: { healthCardNo } });
