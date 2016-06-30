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
public interface Network extends Identified {
    Ip ip();
    Vlan vlan();
    Boolean stp();
    NetworkStatus status();
    Boolean display();
    Integer mtu();
    String[] usages();
    Boolean required();
    Boolean profileRequired();

    @Link DataCenter dataCenter();
    @Link Cluster cluster();
    @Link Qos qos();
    @Link NetworkLabel[] networkLabels();
    @Link Permission[] permissions();
    @Link VnicProfile[] vnicProfiles();
}
