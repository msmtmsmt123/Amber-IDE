#include "amber_os_Utilities.h"
#include <windows.h>

extern "C" JNIEXPORT jstring JNICALL Java_amber_os_Utilities_nativeGetFont(JNIEnv *env, jclass self) {
    NONCLIENTMETRICS ncmMetrics = { sizeof(NONCLIENTMETRICS) };
    LPWSTR szFontName;

    SystemParametersInfo(SPI_GETNONCLIENTMETRICS, 0, &ncmMetrics, 0);
    szFontName = ncmMetrics.lfMessageFont.lfFaceName;
    return env->NewString((jchar*)szFontName, lstrlen(szFontName));
}
