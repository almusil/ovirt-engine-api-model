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

package services;

import annotations.Area;
import org.ovirt.api.metamodel.annotations.In;
import org.ovirt.api.metamodel.annotations.Out;
import org.ovirt.api.metamodel.annotations.Service;
import services.gluster.GlusterHooksService;
import services.gluster.GlusterVolumesService;
import types.Cluster;

@Service
@Area("Virtualization")
public interface ClusterService {
    interface Get {
        @Out Cluster cluster();

        /**
         * Indicates if the results should be filtered according to the permissions of the user.
         */
        @In Boolean filter();
    }

    /**
     * Updates information about the cluster.
     *
     * Only specified fields are updated, others remain unchanged.
     *
     * E.g. update cluster's CPU:
     *
     * [source]
     * ----
     * PUT /ovirt-engine/api/clusters/123
     * ----
     *
     * With request body like:
     *
     * [source,xml]
     * ----
     * <cluster>
     *   <cpu>
     *     <type>Intel Haswell-noTSX Family</type>
     *   </cpu>
     * </cluster>
     * ----
     *
     * @author Jakub Niedermertl <jniederm@redhat.com>
     * @date 14 Sep 2016
     * @status added
     */
    interface Update {
        @In @Out Cluster cluster();

        /**
         * Indicates if the update should be performed asynchronously.
         */
        @In Boolean async();
    }

    /**
     * Removes cluster from the system.
     *
     * [source]
     * ----
     * DELETE /ovirt-engine/api/clusters/00000000-0000-0000-0000-000000000000
     * ----
     *
     * @author Jakub Niedermertl <jniederm@redhat.com>
     * @date 14 Sep 2016
     * @status added
     */
    interface Remove {
        /**
         * Indicates if the remove should be performed asynchronously.
         */
        @In Boolean async();
    }

    interface ResetEmulatedMachine {
        /**
         * Indicates if the reset should be performed asynchronously.
         */
        @In Boolean async();
    }

    @Service AffinityGroupsService affinityGroups();
    @Service AssignedCpuProfilesService cpuProfiles();
    @Service AssignedNetworksService networks();
    @Service AssignedPermissionsService permissions();
    @Service GlusterHooksService glusterHooks();
    @Service GlusterVolumesService glusterVolumes();

    /**
     * A sub collection with all the supported network filters for this cluster.
     */
    @Service NetworkFiltersService networkFilters();

}
