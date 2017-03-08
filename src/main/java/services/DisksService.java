/*
Copyright (c) 2015-2016 Red Hat, Inc.

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

package services;

import annotations.Area;
import org.ovirt.api.metamodel.annotations.In;
import org.ovirt.api.metamodel.annotations.Out;
import org.ovirt.api.metamodel.annotations.Service;
import types.Disk;

/**
 * Manages the collection of disks available in the system.
 *
 * @author Juan Hernandez <juan.hernandez@redhat.com>
 * @date 4 Nov 2016
 * @status added
 */
@Service
@Area("Storage")
public interface DisksService {
    /**
     * Adds a new floating disk.
     *
     * There are three types of disks that can be added - disk image, direct LUN and
     *  https://wiki.openstack.org/wiki/Cinder[Cinder] disk.
     *
     * *Adding a new image disk:*
     *
     * When creating a new floating image <<types/disk,Disk>>, the API requires the `storage_domain`, `provisioned_size`
     * and `format` attributes.
     *
     * To create a new floating image disk with specified `provisioned_size`, `format` and `name` on a storage domain
     * with an id `123`, send a request as follows:
     *
     * [source]
     * ----
     * POST /ovirt-engine/api/disks
     * ----
     *
     * With a request body as follows:
     *
     * [source,xml]
     * ----
     * <disk>
     *   <storage_domains>
     *     <storage_domain id="123"/>
     *   </storage_domains>
     *   <name>mydisk</name>
     *   <provisioned_size>1048576</provisioned_size>
     *   <format>cow</format>
     * </disk>
     * ----
     *
     *
     * *Adding a new direct LUN disk:*
     *
     * When adding a new floating direct LUN via the API, there are two flavors that can be used:
     *
     * . With a `host` element - in this case, the host is used for sanity checks (e.g., that the LUN is visible) and
     * to retrieve basic information about the LUN (e.g., size and serial).
     * . Without a `host` element - in this case, the operation is a database-only operation, and the storage is never
     * accessed.
     *
     * To create a new floating direct LUN disk with a `host` element with an id `123`, specified `alias`, `type` and
     * `logical_unit` with an id `456` (that has the attributes `address`, `port` and `target`),
     * send a request as follows:
     *
     * [source]
     * ----
     * POST /ovirt-engine/api/disks
     * ----
     *
     * With a request body as follows:
     *
     * [source,xml]
     * ----
     * <disk>
     *   <alias>mylun</alias>
     *   <lun_storage>
     *     <host id="123"/>
     *     <type>iscsi</type>
     *     <logical_units>
     *       <logical_unit id="456">
     *         <address>10.35.10.20</address>
     *         <port>3260</port>
     *         <target>iqn.2017-01.com.myhost:444</target>
     *       </logical_unit>
     *     </logical_units>
     *   </lun_storage>
     * </disk>
     * ----
     *
     * To create a new floating direct LUN disk without using a host, remove the `host` element.
     *
     *
     * *Adding a new Cinder disk:*
     *
     * To create a new floating Cinder disk, send a request as follows:
     *
     * [source]
     * ----
     * POST /ovirt-engine/api/disks
     * ----
     *
     * With a request body as follows:
     *
     * [source,xml]
     * ----
     * <disk>
     *   <openstack_volume_type>
     *     <name>myceph</name>
     *   </openstack_volume_type>
     *   <storage_domains>
     *     <storage_domain>
     *       <name>cinderDomain</name>
     *     </storage_domain>
     *   </storage_domains>
     *   <provisioned_size>1073741824</provisioned_size>
     *   <interface>virtio</interface>
     *   <format>raw</format>
     * </disk>
     * ----
     *
     * @author Idan Shaby <ishaby@redhat.com>
     * @author Shani Leviim <sleviim@redhat.com>
     * @date 11 Jan 2017
     * @status added
     */
    interface Add {
        /**
         * The disk.
         *
         * @author Aleksei Slaikovskii <aslaikov@redhat.com>
         * @date 12 Dec 2016
         * @status added
         */
        @In @Out Disk disk();
    }

    /**
     * Get list of disks.
     *
     * [source]
     * ----
     * GET /ovirt-engine/api/disks
     * ----
     *
     * You will get a XML response which will look like this one:
     *
     * [source,xml]
     * ----
     * <disks>
     *   <disk id="123">
     *     <actions>...</actions>
     *     <name>MyDisk</name>
     *     <description>MyDisk description</description>
     *     <link href="/ovirt-engine/api/disks/123/permissions" rel="permissions"/>
     *     <link href="/ovirt-engine/api/disks/123/statistics" rel="statistics"/>
     *     <actual_size>5345845248</actual_size>
     *     <alias>MyDisk alias</alias>
     *     ...
     *     <status>ok</status>
     *     <storage_type>image</storage_type>
     *     <wipe_after_delete>false</wipe_after_delete>
     *     <disk_profile id="123"/>
     *     <quota id="123"/>
     *     <storage_domains>...</storage_domains>
     *   </disk>
     *   ...
     * </disks>
     * ----
     *
     * @author Aleksei Slaikovskii
     * @date 12 Dec 2016
     * @status added
     */
    interface List {
        /**
         * List of retrieved disks.
         *
         * @author Aleksei Slaikovskii <aslaikov@redhat.com>
         * @date 12 Dec 2016
         * @status added
         */
        @Out Disk[] disks();

        /**
         * Sets the maximum number of disks to return. If not specified all the disks are returned.
         */
        @In Integer max();

        /**
         * A query string used to restrict the returned disks.
         */
        @In String search();

        /**
         * Indicates if the search performed using the `search` parameter should be performed taking case into
         * account. The default value is `true`, which means that case is taken into account. If you want to search
         * ignoring case set it to `false`.
         */
        @In Boolean caseSensitive();

        /**
         * Indicates whether to retrieve a list of registered or unregistered disks in the storage domain.
         * To get a list of unregistered disks in the storage domain the call should indicate the unregistered flag.
         * For example, to get a list of unregistered disks the REST API call should look like this:
         *
         * ....
         * GET /ovirt-engine/api/storagedomains/123/disks?unregistered=true
         * ....
         *
         * The default value of the unregistered flag is `false`.
         *
         * IMPORTANT: This parameter only applies to the `disks` sub-collection of attached storage domains, it
         * does not apply to the top level `disks` collection.
         *
         * @author Maor Lipchuk <mlipchuk@redhat.com>
         * @date 8 Mar 2017
         * @status added
         * @since 4.1.1
         */
        @In Boolean unregistered();
    }

    /**
     * Reference to a service managing a specific disk.
     *
     * @author Aleksei Slaikovskii
     * @date 12 Dec 2016
     * @status added
     */
    @Service DiskService disk(String id);
}
