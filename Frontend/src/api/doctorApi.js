// DoctorController  /medicalRecord  (needs ROLE_DOCTOR token)
import { request } from './http.js';

/** POST /medicalRecord/createMedicineRecord — add a prescription for a patient */
export const createMedicalRecord = (token, userName, healthCardNo, data) =>
  request('POST', '/medicalRecord/createMedicineRecord', { token, params: { userName, healthCardNo }, body: data });
