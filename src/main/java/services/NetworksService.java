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
import types.Network;

@Service
@Area("Network")
public interface NetworksService {
    /**
     * Creates new logical network.
     *
     * Creation of a new network requires the `name` and `data_center` elements.
     *
     * For example, to create a network named `mynetwork` for data center `123` send a request like this:
     *
     * [source]
     * ----
     * POST /ovirt-engine/api/networks
     * ----
     *
     * With a request body like this:
     *
     * [source,xml]
     * ----
     * <network>
     *   <name>mynetwork</name>
     *   <data_center id="123"/>
     * </network>
     * ----
     *
     * @author Martin Mucha <mmucha@redhat.com>
     * @date 14 Sep 2016
     * @status added
     */
    interface Add {
        @In @Out Network network();
    }

    interface List {
        @Out Network[] networks();

        /**
         * Sets the maximum number of networks to return. If not specified all the networks are returned.
         */
        @In Integer max();

        /**
         * A query string used to restrict the returned networks.
         */
        @In String search();

        /**
         * Indicates if the search performed using the `search` parameter should be performed taking case into
         * account. The default value is `true`, which means that case is taken into account. If you want to search
         * ignoring case set it to `false`.
         */
        @In Boolean caseSensitive();
    }

    @Service NetworkService network(String id);
}
