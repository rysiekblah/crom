package com.rysiekblah.crom.test.utils;

/**
 * Created by tomek on 11/28/15.
 */

import org.junit.runners.model.InitializationError;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;
import org.robolectric.manifest.AndroidManifest;
import org.robolectric.res.FileFsFile;
import org.robolectric.res.FsFile;

/**
 * More dynamic path resolution.
 *
 * This workaround is only for Mac Users necessary and only if they don't use the $MODULE_DIR$
 * workaround. Follow this issue at https://code.google.com/p/android/issues/detail?id=158015
 */
public class CustomRobolectricRunner extends RobolectricGradleTestRunner {

    public CustomRobolectricRunner(Class<?> klass) throws InitializationError {
        super(klass);
    }

    protected AndroidManifest getAppManifest(Config config) {
        AndroidManifest appManifest = super.getAppManifest(config);
        FsFile androidManifestFile = appManifest.getAndroidManifestFile();

        if (androidManifestFile.exists()) {
            return appManifest;
        } else {
            String moduleRoot = getModuleRootPath(config);
            androidManifestFile = FileFsFile.from(moduleRoot, appManifest.getAndroidManifestFile().getPath());
            FsFile resDirectory = FileFsFile.from(moduleRoot, appManifest.getAndroidManifestFile().getPath().replace("AndroidManifest.xml", "res"));
            FsFile assetsDirectory = FileFsFile.from(moduleRoot, appManifest.getAndroidManifestFile().getPath().replace("AndroidManifest.xml", "assets"));
            return new AndroidManifest(androidManifestFile, resDirectory, assetsDirectory);
        }
    }

    private String getModuleRootPath(Config config) {
        String moduleRoot = config.constants().getResource("").toString().replace("file:", "").replace("jar:", "");
        return moduleRoot.substring(0, moduleRoot.indexOf("/build"));
    }
}

