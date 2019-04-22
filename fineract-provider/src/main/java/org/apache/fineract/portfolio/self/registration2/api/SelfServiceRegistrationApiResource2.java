/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements. See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership. The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package org.apache.fineract.portfolio.self.registration2.api;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.fineract.infrastructure.core.data.CommandProcessingResult;
import org.apache.fineract.infrastructure.core.serialization.DefaultToApiJsonSerializer;
import org.apache.fineract.portfolio.note.data.NoteData;
import org.apache.fineract.portfolio.self.registration.SelfServiceApiConstants;
import org.apache.fineract.portfolio.self.registration.service.SelfServiceRegistrationWritePlatformService;
import org.apache.fineract.useradministration.domain.AppUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Path("/self/registration2")
@Component
@Scope("singleton")
public class SelfServiceRegistrationApiResource2 {

    private final SelfServiceRegistrationWritePlatformService2 selfServiceRegistrationWritePlatformService;
    private final DefaultToApiJsonSerializer<AppUser> toApiJsonSerializer;

    @Autowired
    public SelfServiceRegistrationApiResource2(
            final SelfServiceRegistrationWritePlatformService2 selfServiceRegistrationWritePlatformService,
            final DefaultToApiJsonSerializer<AppUser> toApiJsonSerializer) {
        this.selfServiceRegistrationWritePlatformService = selfServiceRegistrationWritePlatformService;
        this.toApiJsonSerializer = toApiJsonSerializer;
    }

    @POST
    @Produces({ MediaType.APPLICATION_JSON })
    public String createSelfServiceRegistrationRequest2(final String apiRequestBodyAsJson) {
        this.selfServiceRegistrationWritePlatformService.createRegistrationRequest(apiRequestBodyAsJson);
        return SelfServiceApiConstants2.createRequestSuccessMessage;
    }

    @POST
    @Path("user")
    @Produces({ MediaType.APPLICATION_JSON })
    public String createSelfServiceUser2(final String apiRequestBodyAsJson) {
        AppUser user = this.selfServiceRegistrationWritePlatformService.createUser(apiRequestBodyAsJson);
        return this.toApiJsonSerializer.serialize(CommandProcessingResult.resourceResult(user.getId(), null));
    }

}