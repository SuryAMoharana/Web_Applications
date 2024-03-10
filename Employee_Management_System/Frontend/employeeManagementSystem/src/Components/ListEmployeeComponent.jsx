import React, { useEffect, useState } from 'react'
import { deleteEmployee, listEmployees } from '../Services/EmployeeServices';
import { useNavigate } from 'react-router-dom';

const ListEmployeeComponent = () => {
    const [employees, setEmployees] = useState([]);
    const navigator=useNavigate();
    useEffect(()=>{
        getAllEmployees

    },[])

    const getAllEmployees=()=>{
                listEmployees().then((response)=>{
            setEmployees(response.data);
        }).catch(error=>{
            console.error(error);
        })
    }

    const addNewEmployee=()=>{
        navigator("/add-employee")
    }
    
    const updateEmployee=(id)=>{
        navigator(`/edit-employee/${id}`)
    }
    
    const removeEmployee=(id)=>{
        console.log(id)
        deleteEmployee(id).then((response)=>{
            getAllEmployees();
        }).catch(error=>{
            console.error(error)
        })
    }
   
  return (
    <div className='container'>
        <h1 className='text-center'>List of Employees</h1>
        <button className='btn btn-info' onClick={addNewEmployee}>Add Employee</button>        
        <table className='table table-striped-columns table-dark text-center table-hover'>
            <thead>
                <tr>
                    <th>ID</th>
                    <th>FIRSTNAME</th>
                    <th>LASTNAME</th>
                    <th>EMAIL</th>
                    <th>ACTIONS</th>
                </tr>
            </thead>
            <tbody>
                {
                    employees.map(employee=>
                        <tr key={employee.id}>
                            <td>{employee.id}</td>                            
                            <td>{employee.firstName}</td>                            
                            <td>{employee.lastName}</td>                            
                            <td>{employee.email}</td>
                            <td><button className='btn btn-primary' onClick={()=>updateEmployee(employee.id)}>UPDATE</button>
                            <button className='btn btn-danger offset-md-1' onClick={()=>removeEmployee(employee.id)}>DELETE</button>   </td>
                                                     
                        </tr>
                    )
                }
            </tbody>
        </table>
    </div>
  )
}

export default ListEmployeeComponent