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
import types.Host;

@Service
@Area("Infrastructure")
public interface HostsService {
    /**
     * Creates a new host and adds it to the database. The host is created based on the properties of the `host`
     * parameter. The `name`, `address` `root_password` properties are required.
     *
     * [source]
     * ----
     * POST /ovirt-engine/api/hosts
     * ----
     *
     * with body:
     *
     * [source, xml]
     * ----
     * <host>
     *   <name>Host1</name>
     *   <address>192.168.122.154</address>
     *   <root_password>***</root_password>
     * </host>
     * ----
     *
     * The `root_password` element is only included in the client-provided initial representation and is not exposed
     * in the representations returned from subsequent requests.
     *
     * @author Jakub Niedermertl <jniederm@redhat.com>
     * @date 14 Sep 2016
     * @status added
     */
    interface Add {
        /**
         * The host definition from which to create the new host is passed as parameter, and the newly created host
         * is returned.
         */
        @In @Out Host host();

        /**
         * When set to `true` it means this host should deploy also hosted
         * engine components. Missing value is treated as `true` i.e deploy.
         * Omitting this parameter means `false` and will perform no operation
         * in hosted engine area.
         */
        @In Boolean deployHostedEngine();

        /**
         * When set to `true` it means this host should un-deploy hosted engine
         * components and this host will not function as part of the High
         * Availability cluster. Missing value is treated as `true` i.e un-deploy.
         * Omitting this parameter means `false` and will perform no operation
         * in hosted engine area.
         */
        @In Boolean undeployHostedEngine();
    }

    interface List {
        @Out Host[] hosts();

        /**
         * Sets the maximum number of hosts to return. If not specified all the hosts are returned.
         */
        @In Integer max();

        /**
         * A query string used to restrict the returned hosts.
         */
        @In String search();

        /**
         * Indicates if the search performed using the `search` parameter should be performed taking case into
         * account. The default value is `true`, which means that case is taken into account. If you want to search
         * ignoring case set it to `false`.
         */
        @In Boolean caseSensitive();

        /**
         * Indicates if the results should be filtered according to the permissions of the user.
         */
        @In Boolean filter();
    }

    @Service HostService host(String id);
}
