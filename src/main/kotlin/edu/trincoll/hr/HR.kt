package edu.trincoll.hr


class HR(private val employees: List<Employee> = emptyList()) {
    
    fun hire(employee: Employee): HR {
        val newEmployees = employees + employee
        return HR(newEmployees)
    }

    fun fire(id: Int): HR {
        val newEmployees = employees.filter { it.id != id }
        return HR(newEmployees)
    }

    fun payEmployees(): Double {
        return employees.sumOf { it.pay() }
    }
}

