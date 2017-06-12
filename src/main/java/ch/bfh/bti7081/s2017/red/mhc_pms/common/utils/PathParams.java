/**
 * File: ch.bfh.bti7081.s2017.red::PathParams.java
 *
 * @author Aleistar Markóczy
 */
package ch.bfh.bti7081.s2017.red.mhc_pms.common.utils;

import java.util.HashMap;
import java.util.Iterator;

import org.apache.log4j.Logger;

/**
 * The Class PathParams. Homebrew implementation of a Parser for path params.
 */
public class PathParams {

    /**
     * The Constant log.
     */
    static final Logger log = Logger.getRootLogger();

    /**
     * The member params.
     */
    private HashMap<String, String> mParams = null;

    /**
     * Instantiates a new path params. Default constructor used to fill with
     * variables.
     */
    public PathParams() {
        mParams = new HashMap<String, String>();
    }

    /**
     * Instantiates a new path params. The String is parsed to an array of
     * key/value pairs.
     *
     * The Url format as follows:
     * [http://www.myurl.com/xyz]?param1=value1&param2=value2..
     *
     * @param aUrl the reference url
     */
    public PathParams(String aUrl) {
        // Split:
        // [http://www.myurl.com/xyz]?param1=value1&param2=value2 -> ?param1=value1&param2=value2
        mParams = new HashMap<String, String>();
        String[] urlSplit = aUrl.split("\\?");
        if (urlSplit.length == 1) {
            log.debug("No params defined. Url was: " + aUrl);
        }
        // Split:
        // ?param1=value1&param2=value2 -> {param1=value1,param2=value2}
        log.debug("Extracted definition of params: " + urlSplit[1]);
        String[] params = urlSplit[1].split("&");
        for (String iParam : params) {
            // Split:
            // paramN=valueN -> {paramN,valueN}
            log.debug("current param: " + iParam);
            String[] paramDef = iParam.split("=");
            if (paramDef.length < 2) {
                log.warn("Param '" + paramDef[0] + "' was undefined and will be ignored.");
            } else {
                addParam(paramDef[0], paramDef[1]);
            }
        }
    }

    /**
     * Adds a param to this definition.
     *
     * @param aKey the reference key
     * @param aValue the reference value
     * @return the path params
     */
    public PathParams addParam(String aKey, String aValue) {
        mParams.put(aKey, aValue);
        return this;
    }

    /**
     * Gets the param by a key.
     *
     * @param aKey the reference key
     * @return the param
     */
    public String getParam(String aKey) {
        if (!mParams.containsKey(aKey)) {
            log.warn("Requested param '" + aKey + "' was not found in url definition. Definition: '" + getParamString() + "'");
        }
        return mParams.get(aKey);
    }

    /**
     * Gets the string defining all params. Format:
     * ?param1=value1&param2=value2..
     *
     * @return the param string
     */
    public String getParamString() {
        StringBuilder sb = new StringBuilder();
        sb.append("?");
        Iterator<String> lKeys = mParams.keySet().iterator();
        while (lKeys.hasNext()) {
            String key = lKeys.next();
            sb.append(key + "=" + mParams.get(key));
            if (lKeys.hasNext()) {
                sb.append("&");
            }
        }
        return sb.toString();
    }

    @Override
    public String toString() {
        return getParamString();
    }
}
