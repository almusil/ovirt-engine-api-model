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
import services.VirtualFunctionAllowedNetworksService;

@Type
public interface Nic extends Device {
    Boolean linked();
    NicInterface _interface();
    Mac mac();
    Boolean plugged();
    BootProtocol bootProtocol();
    Boolean onBoot();

    /**
     * Usage of this element for creating or updating a NIC is deprecated, use "vnic_profile" instead. It is preserved
     * because it is still in use by the `initialization` element, as a holder for IP addresses and other network
     * details.
     */
    @Link Network network();
    @Link Statistic[] statistics();
    @Link VnicProfile vnicProfile();

    @Link NetworkLabel[] networkLabels();
    @Link NetworkAttachment[] networkAttachments();
    @Link NetworkLabel[] virtualFunctionAllowedLabels();
    @Link Network[] virtualFunctionAllowedNetworks();
    @Link ReportedDevice[] reportedDevices();
}
