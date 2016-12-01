/*
Copyright (c) 2016 Red Hat, Inc.

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
 * This type contains information regarding an image transfer being performed.
 *
 * @author Amit Aviram <aaviram@redhat.com>
 * @author Byron Gravenorst <bgraveno@redhat.com>
 * @date 15 Nov 2016
 * @status updated_by_docs
 * @since 4.0.4
 */
@Type
public interface ImageTransfer extends Identified {
    /**
     * The URL of the proxy server that the user inputs or outputs to. This attribute is
     * available only if the image transfer entity is in the <<types/image_transfer_phase, transferring>>
     * phase. See `phase` for details.
     *
     * @author Amit Aviram <aaviram@redhat.com>
     * @author Byron Gravenorst <bgraveno@redhat.com>
     * @date 15 Nov 2016
     * @status updated_by_docs
     * @since 4.0.4
     */
    String proxyUrl();

    /**
     * The signed ticket that should be attached as an `Authentication` header in the
     * HTTPS request for the proxy server to input or output to (See the `proxy_url` attribute).
     *
     * @author Amit Aviram <aaviram@redhat.com>
     * @author Byron Gravenorst <bgraveno@redhat.com>
     * @date 15 Nov 2016
     * @status updated_by_docs
     * @since 4.0.4
     */
    String signedTicket();

    /**
     * The current phase of the image transfer in progress. Each transfer needs a managed
     * session, which must be opened for the user to input or output an image.
     * Please refer to <<services/image_transfer, image transfer>> for further
     * documentation.
     *
     * @author Amit Aviram <aaviram@redhat.com>
     * @author Byron Gravenorst <bgraveno@redhat.com>
     * @date 15 Nov 2016
     * @status updated_by_docs
     * @since 4.0.4
     */
    ImageTransferPhase phase();

    /**
     * The image which is targeted for I/O.
     *
     * @author Amit Aviram <aaviram@redhat.com>
     * @author Byron Gravenorst <bgraveno@redhat.com>
     * @date 15 Nov 2016
     * @status updated_by_docs
     * @since 4.0.4
     */
    @Link Image image();

    /**
     * The host which will be used to write to the image which is targeted for input or output.
     *
     * @author Amit Aviram <aaviram@redhat.com>
     * @author Byron Gravenorst <bgraveno@redhat.com>
     * @date 15 Nov 2016
     * @status updated_by_docs
     * @since 4.0.4
     */
    @Link Host host();
}
