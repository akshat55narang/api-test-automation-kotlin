import org.slf4j.LoggerFactory
import org.testng.ITestResult
import org.testng.annotations.AfterMethod
import org.testng.annotations.BeforeMethod
import java.lang.reflect.Method
import kotlin.math.log

class BaseTest {

    private val logger = LoggerFactory.getLogger(BaseTest::class.java)

    @BeforeMethod
    fun setup(method: Method) {
        logger.info("********** Executing test ${method.name} **********")
    }

    @AfterMethod
    fun tearDown(iTestResult: ITestResult) {
        when (iTestResult.status) {
            1 -> logger.info("********** Test : ${iTestResult.name} completed with status : ${Status.SUCCESS} **********")
            2 -> logger.info("********** Test : ${iTestResult.name} completed with status : ${Status.FAILURE} **********")
            3 -> logger.info("********** Test : ${iTestResult.name} completed with status : ${Status.SKIPPED} **********")
            else -> logger.info("********** Test : ${iTestResult.name} completed with status code : ${iTestResult.status} **********")
        }

    }

    enum class Status {
        SUCCESS,
        FAILURE,
        SKIPPED
    }
}