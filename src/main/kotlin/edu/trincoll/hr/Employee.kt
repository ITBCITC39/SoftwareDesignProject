package edu.trincoll.hr

abstract class Employee(
    val name: String,
    val id: Int
) : Comparable<Employee> {

    // Abstract method for pay
    abstract fun pay(): Double

    // Implementing compareTo method
    override fun compareTo(other: Employee): Int {
        return this.id - other.id
    }

    // Overriding toString method
    override fun toString(): String {
        return "Employee(name='$name', id=$id)"
    }
}
