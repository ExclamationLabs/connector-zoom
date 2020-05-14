/*
    Copyright 2020 Exclamation Labs

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

package com.exclamationlabs.connid.zoom.client;

import com.exclamationlabs.connid.zoom.ZoomConnection;
import org.apache.http.client.methods.HttpDelete;

public class RemoveGroupFromUserClient {

    private final ZoomConnection connection;

    public RemoveGroupFromUserClient(ZoomConnection input) {
        connection = input;
    }

    public void execute(String groupId, String userId) {

        HttpDelete request = connection.createDeleteRequest("/groups/" + groupId + "/members/" + userId);
        connection.executeRequest(request, null);
    }
}
