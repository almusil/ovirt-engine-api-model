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
 * Type representation of a cluster.
 *
 * A JSON representation of a cluster
 *
 * [source]
 * ----
 * {
 *   "cluster" : [ {
 *     "ballooning_enabled" : "false",
 *     "cpu" : {
 *       "architecture" : "x86_64",
 *       "type" : "Intel SandyBridge Family"
 *     },
 *     "custom_scheduling_policy_properties" : {
 *       "property" : [ {
 *         "name" : "HighUtilization",
 *         "value" : "80"
 *       }, {
 *         "name" : "CpuOverCommitDurationMinutes",
 *         "value" : "2"
 *       } ]
 *     },
 *     "error_handling" : {
 *       "on_error" : "migrate"
 *     },
 *     "fencing_policy" : {
 *       "enabled" : "true",
 *       "skip_if_connectivity_broken" : {
 *         "enabled" : "false",
 *         "threshold" : "50"
 *       },
 *       "skip_if_gluster_bricks_up" : "false",
 *       "skip_if_gluster_quorum_not_met" : "false",
 *       "skip_if_sd_active" : {
 *         "enabled" : "false"
 *       }
 *     },
 *     "gluster_service" : "false",
 *     "ha_reservation" : "false",
 *     "ksm" : {
 *       "enabled" : "true",
 *       "merge_across_nodes" : "true"
 *     },
 *     "maintenance_reason_required" : "false",
 *     "memory_policy" : {
 *       "over_commit" : {
 *         "percent" : "100"
 *       },
 *       "transparent_hugepages" : {
 *         "enabled" : "true"
 *       }
 *     },
 *     "migration" : {
 *       "auto_converge" : "inherit",
 *       "bandwidth" : {
 *         "assignment_method" : "auto"
 *       },
 *       "compressed" : "inherit",
 *       "policy" : {
 *         "id" : "00000000-0000-0000-0000-000000000000"
 *       }
 *     },
 *     "optional_reason" : "false",
 *     "required_rng_sources" : {
 *       "required_rng_source" : [ "random" ]
 *     },
 *     "switch_type" : "legacy",
 *     "threads_as_cores" : "false",
 *     "trusted_service" : "false",
 *     "tunnel_migration" : "false",
 *     "version" : {
 *       "major" : "4",
 *       "minor" : "1"
 *     },
 *     "virt_service" : "true",
 *     "data_center" : {
 *       "href" : "/ovirt-engine/api/datacenters/123",
 *       "id" : "123"
 *     },
 *     "mac_pool" : {
 *       "href" : "/ovirt-engine/api/macpools/456",
 *       "id" : "456"
 *     },
 *     "scheduling_policy" : {
 *       "href" : "/ovirt-engine/api/schedulingpolicies/789",
 *       "id" : "789"
 *     },
 *     "actions" : {
 *       "link" : [ {
 *         "href" : "/ovirt-engine/api/clusters/234/resetemulatedmachine",
 *         "rel" : "resetemulatedmachine"
 *       } ]
 *     },
 *     "name" : "Default",
 *     "description" : "The default server cluster",
 *     "href" : "/ovirt-engine/api/clusters/234",
 *     "id" : "234",
 *     "link" : [ {
 *       "href" : "/ovirt-engine/api/clusters/234/permissions",
 *       "rel" : "permissions"
 *     }, {
 *       "href" : "/ovirt-engine/api/clusters/234/cpuprofiles",
 *       "rel" : "cpuprofiles"
 *     }, {
 *       "href" : "/ovirt-engine/api/clusters/234/networkfilters",
 *       "rel" : "networkfilters"
 *     }, {
 *       "href" : "/ovirt-engine/api/clusters/234/networks",
 *       "rel" : "networks"
 *     }, {
 *       "href" : "/ovirt-engine/api/clusters/234/affinitygroups",
 *       "rel" : "affinitygroups"
 *     }, {
 *       "href" : "/ovirt-engine/api/clusters/234/glusterhooks",
 *       "rel" : "glusterhooks"
 *     }, {
 *       "href" : "/ovirt-engine/api/clusters/234/glustervolumes",
 *       "rel" : "glustervolumes"
 *     } ]
 *   } ]
 * }
 * ----
 *
 * @author Piotr Kliczewski <pkliczew@redhat.com>
 * @date 12 Dec 2016
 * @status added
 */
@Type
public interface Cluster extends Identified {
    Cpu cpu();
    MemoryPolicy memoryPolicy();

    /**
     * The compatibility version of the cluster.
     *
     * All hosts in this cluster must support at least this compatibility version.
     *
     * For example:
     *
     * [source]
     * ----
     * GET /ovirt-engine/api/clusters/123
     * ----
     *
     * Will respond:
     *
     * [source,xml]
     * ----
     * <cluster>
     *   ...
     *   <version>
     *     <major>4</major>
     *     <minor>0</minor>
     *   </version>
     *   ...
     * </cluster>
     * ----
     *
     * To update the compatibility version, use:
     *
     * [source]
     * ----
     * PUT /ovirt-engine/api/clusters/123
     * ----
     *
     * With a request body:
     *
     * [source,xml]
     * ----
     * <cluster>
     *   <version>
     *     <major>4</major>
     *     <minor>1</minor>
     *   </version>
     * </cluster>
     * ----
     *
     * In order to update the cluster compatibility version, all hosts in the cluster must support the new compatibility version.
     *
     * @author Tomas Jelinek <tjelinek@redhat.com>
     * @author Byron Gravenorst <bgraveno@redhat.com>
     * @date 25 Oct 2016
     * @status updated_by_docs
     */
    Version version();

    Version[] supportedVersions();
    ErrorHandling errorHandling();
    Boolean virtService();
    Boolean glusterService();
    Boolean threadsAsCores();
    Boolean tunnelMigration();
    Boolean trustedService();
    Boolean haReservation();
    Boolean optionalReason();
    Boolean maintenanceReasonRequired();
    Boolean ballooningEnabled();
    Display display();
    Ksm ksm();
    SerialNumber serialNumber();
    RngSource[] requiredRngSources();

    /**
     * Custom fencing policy can be defined for a cluster.
     *
     * Here is an example:
     *
     * [source]
     * ----
     * PUT /ovirt-engine/api/cluster/123
     * ----
     *
     * With request body:
     *
     * [source,xml]
     * ----
     * <cluster>
     *   <fencing_policy>
     *     <enabled>true</enabled>
     *     <skip_if_sd_active>
     *       <enabled>false</enabled>
     *     </skip_if_sd_active>
     *     <skip_if_connectivity_broken>
     *       <enabled>false</enabled>
     *       <threshold>50</threshold>
     *     </skip_if_connectivity_broken>
     *   </fencing_policy>
     * </cluster>
     * ----
     *
     * @author Oved Ourfali <oourfali@redhat.com>
     * @date 29 Nov 2016
     * @status added
     */
    FencingPolicy fencingPolicy();
    MigrationOptions migration();

    /**
     * Custom scheduling policy properties of the cluster.
     * These optional properties override the properties of the
     * scheduling policy specified by the `scheduling_policy` link,
     * and apply only for this specific cluster.
     *
     * For example, to update the custom properties of the cluster,
     * send a request:
     *
     * [source]
     * ----
     * PUT /ovirt-engine/api/clusters/123
     * ----
     *
     * With a request body:
     *
     * [source,xml]
     * ----
     * <cluster>
     *   <custom_scheduling_policy_properties>
     *     <property>
     *       <name>HighUtilization</name>
     *       <value>70</value>
     *     </property>
     *   </custom_scheduling_policy_properties>
     * </cluster>
     * ----
     *
     * Update operations using the `custom_scheduling_policy_properties` attribute
     * will not update the the properties of the scheduling policy specified by
     * the `scheduling_policy` link,
     * they will only be reflected on this specific cluster.
     *
     * @author Yanir Quinn <yquinn@redhat.com>
     * @author Byron Gravenorst <bgraveno@redhat.com>
     * @date 25 Oct 2016
     * @status updated_by_docs
     * @since 4.0.6
     */
    Property[] customSchedulingPolicyProperties();

    /**
     * Type of switch to be used by all networks in given cluster.
     *
     * @author Byron Gravenorst <bgraveno@redhat.com>
     * @date 25 Oct 2016
     * @status updated_by_docs
     */
    SwitchType switchType();

    /**
     * Reference to the default scheduling policy used by
     * this cluster.
     *
     * NOTE: The scheduling policy properties are taken by
     * default from the referenced scheduling policy, but
     * they are overridden by the properties specified in
     * the `custom_scheduling_policy_properties` attribute
     * for this cluster.
     *
     * @author Yanir Quinn <yquinn@redhat.com>
     * @author Byron Gravenorst <bgraveno@redhat.com>
     * @date 25 Oct 2016
     * @status updated_by_docs
     */
    @Link SchedulingPolicy schedulingPolicy();

    @Link DataCenter dataCenter();
    @Link Network managementNetwork();
    @Link AffinityGroup[] affinityGroups();
    @Link CpuProfile[] cpuProfiles();
    @Link GlusterHook[] glusterHooks();
    @Link GlusterVolume[] glusterVolumes();
    @Link NetworkFilter[] networkFilters();
    @Link Network[] networks();
    @Link Permission[] permissions();
}
