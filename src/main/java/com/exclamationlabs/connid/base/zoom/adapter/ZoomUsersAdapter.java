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

package com.exclamationlabs.connid.base.zoom.adapter;

import com.exclamationlabs.connid.base.connector.adapter.AdapterValueTypeConverter;
import com.exclamationlabs.connid.base.connector.adapter.BaseAdapter;
import com.exclamationlabs.connid.base.connector.attribute.ConnectorAttribute;
import com.exclamationlabs.connid.base.zoom.model.ZoomUser;
import org.identityconnectors.framework.common.objects.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static com.exclamationlabs.connid.base.connector.attribute.ConnectorAttributeDataType.*;
import static org.identityconnectors.framework.common.objects.AttributeInfo.Flags.MULTIVALUED;
import static org.identityconnectors.framework.common.objects.AttributeInfo.Flags.NOT_UPDATEABLE;
import static com.exclamationlabs.connid.base.zoom.attribute.ZoomUserAttribute.*;

public class ZoomUsersAdapter extends BaseAdapter<ZoomUser> {

    @Override
    public ObjectClass getType() {
        return ObjectClass.ACCOUNT;
    }

    @Override
    public Class<ZoomUser> getIdentityModelClass() {
        return ZoomUser.class;
    }

    @Override
    public List<ConnectorAttribute> getConnectorAttributes() {
        List<ConnectorAttribute> result = new ArrayList<>();
        result.add(new ConnectorAttribute(USER_ID.name(), STRING, NOT_UPDATEABLE));
        result.add(new ConnectorAttribute(FIRST_NAME.name(), STRING));
        result.add(new ConnectorAttribute(LAST_NAME.name(), STRING));
        result.add(new ConnectorAttribute(EMAIL.name(), STRING));

        result.add(new ConnectorAttribute(PASSWORD.name(), STRING, NOT_UPDATEABLE));
        result.add(new ConnectorAttribute(LANGUAGE.name(), STRING));
        result.add(new ConnectorAttribute(TIME_ZONE.name(), STRING));
        result.add(new ConnectorAttribute(PHONE_NUMBER.name(), STRING));
        result.add(new ConnectorAttribute(STATUS.name(), STRING));
        result.add(new ConnectorAttribute(TYPE.name(), INTEGER, NOT_UPDATEABLE));
        result.add(new ConnectorAttribute(CREATED_AT.name(), STRING, NOT_UPDATEABLE));
        result.add(new ConnectorAttribute(LAST_LOGIN_TIME.name(), STRING, NOT_UPDATEABLE));
        result.add(new ConnectorAttribute(VERIFIED.name(), STRING, NOT_UPDATEABLE));
        result.add(new ConnectorAttribute(PERSONAL_MEETING_ID.name(), LONG, NOT_UPDATEABLE));

        result.add(new ConnectorAttribute(GROUP_IDS.name(), ASSIGNMENT_IDENTIFIER, MULTIVALUED));

        return result;
    }

    @Override
    protected ZoomUser constructModel(Set<Attribute> attributes, boolean creation) {
        ZoomUser user = new ZoomUser();
        user.setId(AdapterValueTypeConverter.getIdentityIdAttributeValue(attributes));
        user.setFirstName(AdapterValueTypeConverter.getSingleAttributeValue(String.class, attributes, FIRST_NAME));
        user.setLastName(AdapterValueTypeConverter.getSingleAttributeValue(String.class, attributes, LAST_NAME));
        user.setEmail(AdapterValueTypeConverter.getSingleAttributeValue(String.class, attributes, EMAIL));
        user.setTimezone(AdapterValueTypeConverter.getSingleAttributeValue(String.class, attributes, TIME_ZONE));

        user.setPassword(AdapterValueTypeConverter.getSingleAttributeValue(String.class, attributes, PASSWORD));
        user.setLanguage(AdapterValueTypeConverter.getSingleAttributeValue(String.class, attributes, LANGUAGE));
        user.setTimezone(AdapterValueTypeConverter.getSingleAttributeValue(String.class, attributes, TIME_ZONE));
        user.setPhoneNumber(AdapterValueTypeConverter.getSingleAttributeValue(String.class, attributes, PHONE_NUMBER));
        user.setStatus(AdapterValueTypeConverter.getSingleAttributeValue(String.class, attributes, STATUS));
        user.setType(AdapterValueTypeConverter.getSingleAttributeValue(Integer.class, attributes, TYPE));
        user.setCreatedAt(AdapterValueTypeConverter.getSingleAttributeValue(String.class, attributes, CREATED_AT));
        user.setLastLoginTime(AdapterValueTypeConverter.getSingleAttributeValue(String.class, attributes, LAST_LOGIN_TIME));
        user.setVerified(AdapterValueTypeConverter.getSingleAttributeValue(String.class, attributes, VERIFIED));
        user.setPersonalMeetingId(AdapterValueTypeConverter.getSingleAttributeValue(Long.class, attributes, PERSONAL_MEETING_ID));
        user.setGroupIds(readAssignments(attributes, GROUP_IDS));

        return user;
    }

    @Override
    protected List<Attribute> constructAttributes(ZoomUser user) {
        List<Attribute> attributes = new ArrayList<>();

        attributes.add(AttributeBuilder.build(USER_ID.name(), user.getId()));
        attributes.add(AttributeBuilder.build(EMAIL.name(), user.getEmail()));
        attributes.add(AttributeBuilder.build(FIRST_NAME.name(), user.getFirstName()));
        attributes.add(AttributeBuilder.build(LAST_NAME.name(), user.getLastName()));
        attributes.add(AttributeBuilder.build(LANGUAGE.name(), user.getLanguage()));
        attributes.add(AttributeBuilder.build(TIME_ZONE.name(), user.getTimezone()));
        attributes.add(AttributeBuilder.build(STATUS.name(), user.getStatus()));
        attributes.add(AttributeBuilder.build(TYPE.name(), user.getType()));
        attributes.add(AttributeBuilder.build(PHONE_NUMBER.name(), user.getPhoneNumber()));
        attributes.add(AttributeBuilder.build(CREATED_AT.name(), user.getCreatedAt()));
        attributes.add(AttributeBuilder.build(LAST_LOGIN_TIME.name(), user.getLastLoginTime()));
        attributes.add(AttributeBuilder.build(VERIFIED.name(), user.getVerified()));
        attributes.add(AttributeBuilder.build(PERSONAL_MEETING_ID.name(), user.getPersonalMeetingId()));
        attributes.add(AttributeBuilder.build(GROUP_IDS.name(), user.getGroupIds()));

        return attributes;
    }
}
