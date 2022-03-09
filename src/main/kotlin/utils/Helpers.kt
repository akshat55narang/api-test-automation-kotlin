package utils

import org.apache.commons.lang3.StringUtils.isBlank
import org.slf4j.LoggerFactory

object Helpers {
    private val logger = LoggerFactory.getLogger(Helpers::class.java)

    fun getParameterValue(variableName: String, defaultValue: String?): String {
        var resolvedValue = defaultValue
        var env = System.getenv(variableName)
        if (env != null) {
            resolvedValue = env
        }
        var result = System.getProperty(variableName, resolvedValue)
        if (isBlank(result)) {
            logger.info("empty value supplied for system property $variableName, reverting to default value")
            result = resolvedValue
        }
        return result
    }

}