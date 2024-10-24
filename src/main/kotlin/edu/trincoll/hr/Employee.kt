package edu.trincoll.hr

abstract class Employee(
     val name: String,
    val id: Int
) : Comparable<Employee> {

    // Abstract method for pay
    abstract fun pay(): Double

    // Implementing compareTo method
    override fun compareTo(other: Employee): Int {
        val payComparison = pay().compareTo(other.pay())
        if (payComparison != 0) {
            return payComparison
        }

// If pay is the same, compare by name
        val nameComparison = name.compareTo(other.name)
        if (nameComparison != 0) {
            return nameComparison
        }

// If both pay and name are the same, compare by id
        return id.compareTo(other.id)
    }

    // Overriding toString method
    override fun toString(): String {
        return "Employee(name='$name', id=$id)"
    }
}

