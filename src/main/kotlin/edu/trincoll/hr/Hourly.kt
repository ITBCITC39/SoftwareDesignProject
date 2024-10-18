package edu.trincoll.hr


class Hourly (
    name: String,
    id: Int,
    private val rate: Double,
    private val hours: Double = 80.0
) : Employee(name, id) {

    override fun pay(): Double {
        return rate * hours
    }

    override fun toString(): String {
        return "Hourly(name='$name', id=$id, rate=$rate, hours=$hours)"
    }
}
