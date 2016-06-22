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
import types.Cluster;
import types.StorageDomain;
import types.Vm;

@Service
@Area("Storage")
public interface StorageDomainVmService {
    interface Get {
        @Out Vm vm();
    }

    interface Import {
        /**
         * Indicates if the identifiers of the imported virtual machine
         * should be regenerated.
         *
         * By default when a virtual machine is imported the identifiers
         * are preserved. This means that the same virtual machine can't
         * be imported multiple times, as that identifiers needs to be
         * unique. To allow importing the same machine multiple times set
         * this parameter to `true`, as the default is `false`.
         */
        @In Boolean clone();

        @In Cluster cluster();
        @In StorageDomain storageDomain();
        @In Vm vm();

        /**
         * Indicates of the snapshots of the virtual machine that is imported
         * should be collapsed, so that the result will be a virtual machine
         * without snapshots.
         *
         * This parameter is optional, and if it isn't explicity specified the
         * default value is `false`.
         */
        @In Boolean collapseSnapshots();

        /**
         * Indicates if the import should be performed asynchronously.
         */
        @In Boolean async();
    }

    interface Register {
        @In Boolean clone();
        @In Cluster cluster();
        @In Vm vm();

        /**
         * Indicates if the registration should be performed asynchronously.
         */
        @In Boolean async();
    }

    interface Remove {
        /**
         * Indicates if the remove should be performed asynchronously.
         */
        @In Boolean async();
    }

    @Service StorageDomainContentDisksService disks();
}
