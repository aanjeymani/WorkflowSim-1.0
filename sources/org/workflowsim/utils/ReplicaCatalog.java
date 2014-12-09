/*
 * 
 *   Copyright 2012-2013 University Of Southern California
 * 
 *   Licensed under the Apache License, Version 2.0 (the "License");
 *   you may not use this file except in compliance with the License.
 *   You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 *   Unless required by applicable law or agreed to in writing,
 *   software distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
 * 
 */
package org.workflowsim.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * ReplicaCatalog stores all the data information and where (site) there are
 *
 * @author Weiwei Chen
 * @since WorkflowSim Toolkit 1.0
 * @date Apr 9, 2013
 */
public class ReplicaCatalog {

    /**
     * File System
     */
    public enum FileSystem {

        SHARED, LOCAL, RANDOM
    }
    /**
     * Map from file name to a file object
     */
    private static Map FileName2File;
    /**
     * The selection of file.system
     */
    private static FileSystem fileSystem;
    /**
     * Map from file to a list of data storage
     */
    private static Map dataReplicaCatalog;

    /**
     * Initialize a ReplicaCatalog
     *
     * @param fs the type of file system
     */
    public static void init(FileSystem fs) {
        fileSystem = fs;
        dataReplicaCatalog = new HashMap< String, List>();
        FileName2File = new HashMap<String, org.cloudbus.cloudsim.File>();
    }

    /**
     * Gets the file system
     *
     * @return file system
     */
    public static FileSystem getFileSystem() {
        return fileSystem;
    }

    /**
     * Gets the file object based its file name
     *
     * @param fileName, file name
     * @return file object
     */
    public static org.cloudbus.cloudsim.File getFile(String fileName) {
        return (org.cloudbus.cloudsim.File) FileName2File.get(fileName);
    }

    /**
     * Adds a file name and the associated file object
     *
     * @param fileName, the file name
     * @param file , the file object
     */
    public static void setFile(String fileName, org.cloudbus.cloudsim.File file) {
        FileName2File.put(fileName, file);
    }

    /**
     * Checks whether a file exists
     *
     * @param fileName file name
     * @return boolean, whether the file exist
     */
    public static boolean containsFile(String fileName) {
        return FileName2File.containsKey(fileName);
    }

    /**
     * Gets the list of storages a file exists
     *
     * @param file the file object
     * @return list of storages
     */
    public static List getStorageList(String file) {
        return (List) dataReplicaCatalog.get(file);
    }

    /**
     * Adds a file to a storage
     *
     * @param file, a file object
     * @param storage , the storage associated with this file
     */
    public static void addStorageList(String file, String storage) {
        if (!dataReplicaCatalog.containsKey(file)) {
            dataReplicaCatalog.put(file, new ArrayList());
        }
        List list = getStorageList(file);
        if (!list.contains(storage)) {
            list.add(storage);
        }
    }
}
