import org.slf4j.LoggerFactory
import org.testng.ITestResult
import org.testng.annotations.AfterMethod
import org.testng.annotations.BeforeMethod
import java.lang.reflect.Method

open class BaseTest {

    private val logger = LoggerFactory.getLogger(BaseTest::class.java)

    @BeforeMethod
    fun setup(method: Method) {
        logger.info("********** Executing test ${method.name} **********")
    }

    @AfterMethod
    fun tearDown(testResult: ITestResult) {
        when (testResult.status) {
            1 -> logger.info("********** Test : ${testResult.name} completed with status : ${Status.SUCCESS} **********")
            2 -> logger.info("********** Test : ${testResult.name} completed with status : ${Status.FAILURE} **********")
            3 -> logger.info("********** Test : ${testResult.name} completed with status : ${Status.SKIPPED} **********")
            else -> logger.info("********** Test : ${testResult.name} completed with status code : ${testResult.status} **********")
        }
    }

    enum class Status {
        SUCCESS,
        FAILURE,
        SKIPPED
    }
}