/*
Copyright (c) 2015 Red Hat, Inc.

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

  http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
*/

package types;

import org.ovirt.api.metamodel.annotations.Link;
import org.ovirt.api.metamodel.annotations.Type;

/**
 * Represents a storage server connection.
 *
 * Example XML representation:
 *
 * [source,xml]
 * ----
 * <storage_connection id="123">
 *   <address>mynfs.example.com</address>
 *   <type>nfs</type>
 *   <path>/exports/mydata</path>
 * </storage_connection>
 * ----
 *
 * @author Daniel Erez <derez@redhat.com>
 * @author Tahlia Richardson <trichard@redhat.com>
 * @date 29 Nov 2016
 * @status updated_by_docs
 */
@Type
public interface StorageConnection extends Identified {
    // Common to all types of storage connections:
    String address();
    StorageType type();

    // For NFS:
    String path();
    String mountOptions();
    String vfsType();
    NfsVersion nfsVersion();
    Integer nfsTimeo();
    Integer nfsRetrans();

    // For iSCSI:
    Integer port();
    String target();
    String username();
    String password();
    String portal();

    // Link to the host:
    @Link Host host();
}
