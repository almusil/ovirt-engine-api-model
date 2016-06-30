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

package types;

import org.ovirt.api.metamodel.annotations.Link;
import org.ovirt.api.metamodel.annotations.Type;

@Type
public interface OpenStackNetworkProvider extends OpenStackProvider {
    NetworkPluginType pluginType();
    AgentConfiguration agentConfiguration();

    /**
     * Indicates whether the provider is read only.
     * A read-only provider does not allow adding, modifying or deleting of
     * networks or subnets.
     * Port-related operations are allowed, as they are required for the
     * provisioning of virtual nics.
     */
    Boolean readOnly();

    /**
     * The type of provider.
     */
    OpenStackNetworkProviderType type();

    @Link Certificate[] certificates();
    @Link OpenStackNetwork[] networks();
    @Link OpenStackSubnet[] subnets();
}
