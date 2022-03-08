package airline

import org.testng.annotations.DataProvider

class DataProvider {

    @DataProvider(name = "airlineIds", parallel = true)
    fun airlineData(): Array<Array<Any>> {
        return arrayOf(
            arrayOf(1),
            arrayOf(7),
            arrayOf(10),
            arrayOf(20)
        )
    }
}