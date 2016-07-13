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

import java.util.Date;

@Type
public interface VmBase extends Identified {
    VmType type();
    Integer memory();
    Cpu cpu();
    Integer cpuShares();
    Bios bios();
    OperatingSystem os();
    Date creationTime();
    String origin();
    Boolean stateless();
    Boolean deleteProtected();
    HighAvailability highAvailability();
    Display display();
    Sso sso();
    RngDevice rngDevice();
    Console console();
    Domain domain();
    Usb usb();
    Boolean soundcardEnabled();
    Boolean tunnelMigration();
    Integer migrationDowntime();
    VirtioScsi virtioScsi();
    SerialNumber serialNumber();
    Boolean startPaused();
    MigrationOptions migration();
    Io io();
    CustomProperty[] customProperties();
    String customEmulatedMachine();
    String customCpuModel();
    TimeZone timeZone();
    Icon smallIcon();
    Icon largeIcon();
    Initialization initialization();
    MemoryPolicy memoryPolicy();

    /**
     * Virtual machine custom compatibility version.
     *
     * This field allows to customize a virtual machine to its own compatibility version.  If
     * `custom_compatibility_version` is set, it overrides the cluster's compatibility version
     * for this particular virtual machine.
     *
     * The compatibility version of a virtual machine is limited by the data center the virtual
     * machine resides in and is checked against capabilities of the host the virtual machine is
     * planned to run on.
     */
    Version customCompatibilityVersion();

    @Link Cluster cluster();
    @Link StorageDomain storageDomain();
    @Link CpuProfile cpuProfile();
}
