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

package com.exclamationlabs.connid.base.zoom.driver.rest;

import com.exclamationlabs.connid.base.connector.driver.DriverInvocator;
import com.exclamationlabs.connid.base.zoom.model.ZoomGroup;
import com.exclamationlabs.connid.base.zoom.model.response.ListGroupsResponse;
import org.identityconnectors.framework.common.exceptions.ConnectorException;

import java.util.List;
import java.util.Map;

public class ZoomGroupsInvocator implements DriverInvocator<ZoomDriver, ZoomGroup> {

    @Override
    public String create(ZoomDriver zoomDriver, ZoomGroup groupModel) throws ConnectorException {

        ZoomGroup newGroup = zoomDriver.executePostRequest("/groups",
                ZoomGroup.class, groupModel).getResponseObject();

        if (newGroup == null || newGroup.getId() == null) {
            throw new ConnectorException("Response from group creation was invalid");
        }

        return newGroup.getId();
    }

    @Override
    public void update(ZoomDriver zoomDriver, String groupId, ZoomGroup groupModel) throws ConnectorException {

        ZoomGroup modifyGroup = new ZoomGroup();
        // Cannot send key in update JSON, and name is the only field to update,
        // so create a new object w/ just the name set
        modifyGroup.setName(groupModel.getName());

        zoomDriver.executePatchRequest("/groups/" + groupModel.getId(), null, groupModel);
    }

    @Override
    public void delete(ZoomDriver zoomDriver, String groupId) throws ConnectorException {
        zoomDriver.executeDeleteRequest("/groups/" + groupId, null);
    }

    @Override
    public List<ZoomGroup> getAll(ZoomDriver zoomDriver, Map<String, Object> dataMap) throws ConnectorException {
        ListGroupsResponse response = zoomDriver.executeGetRequest(
                "/groups", ListGroupsResponse.class).getResponseObject();
        return response.getGroups();
    }

    @Override
    public ZoomGroup getOne(ZoomDriver zoomDriver, String groupId, Map<String, Object> dataMap) throws ConnectorException {
        return zoomDriver.executeGetRequest(
                "/groups/" + groupId, ZoomGroup.class).getResponseObject();
    }

}
