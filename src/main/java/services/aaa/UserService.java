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

package services.aaa;

import annotations.Area;
import org.ovirt.api.metamodel.annotations.In;
import org.ovirt.api.metamodel.annotations.Out;
import org.ovirt.api.metamodel.annotations.Service;
import services.AssignedPermissionsService;
import services.AssignedRolesService;
import services.AssignedTagsService;
import types.User;

/**
 * A service to manage a user in the system.
 * Use this service to either get users details or remove users.
 * In order to add new users please use
 * <<services/users>>.
 *
 * @author Oved Ourfali <oourfali@redhat.com>
 * @date 28 Nov 2016
 * @status added
 */
@Service
@Area("Infrastructure")
public interface UserService {
    interface Get {
        @Out User user();
    }

    interface Remove {
        /**
         * Indicates if the remove should be performed asynchronously.
         */
        @In Boolean async();
    }

    @Service AssignedRolesService roles();
    @Service AssignedPermissionsService permissions();
    @Service AssignedTagsService tags();
    @Service SshPublicKeysService sshPublicKeys();
}
