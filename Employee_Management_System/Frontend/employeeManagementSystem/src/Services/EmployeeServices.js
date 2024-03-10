import axios from "axios";


export const listEmployees=()=>axios.get("http://localhost:8080/api/employees/getAll");

export const createEmployee=(employee)=>axios.post("http://localhost:8080/api/employees/create", employee);

export const getEmployee=(employeeId)=>axios.get("http://localhost:8080/api/employees/getById"+"/"+employeeId);

export const updateEmployee=(employeeId, employee)=>axios.put("http://localhost:8080/api/employees/updateEmployee"+"/"+employeeId, employee)

export const deleteEmployee=(employeeId)=>axios.delete("http://localhost:8080/api/employees/deleteEmployee"+"/"+employeeId)