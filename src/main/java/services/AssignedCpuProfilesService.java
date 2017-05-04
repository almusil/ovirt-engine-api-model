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
import types.CpuProfile;

@Service
@Area("Virtualization")
public interface AssignedCpuProfilesService {
    interface Add {
        @In @Out CpuProfile profile();
    }

    /**
     * List the CPU profiles assigned to the cluster.
     *
     * The order of the returned CPU profiles isn't guaranteed.
     *
     * @author Juan Hernandez <juan.hernandez@redhat.com>
     * @date 15 Apr 2017
     * @status added
     */
    interface List {
        @Out CpuProfile[] profiles();

        /**
         * Sets the maximum number of profiles to return. If not specified all the profiles are returned.
         */
        @In Integer max();
    }

    @Service AssignedCpuProfileService profile(String id);
}
