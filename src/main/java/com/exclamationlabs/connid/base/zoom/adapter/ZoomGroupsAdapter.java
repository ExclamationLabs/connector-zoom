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
import com.exclamationlabs.connid.base.zoom.model.ZoomGroup;
import org.identityconnectors.framework.common.objects.Attribute;
import org.identityconnectors.framework.common.objects.AttributeBuilder;
import org.identityconnectors.framework.common.objects.ObjectClass;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static com.exclamationlabs.connid.base.connector.attribute.ConnectorAttributeDataType.*;
import static org.identityconnectors.framework.common.objects.AttributeInfo.Flags.NOT_UPDATEABLE;
import static com.exclamationlabs.connid.base.zoom.attribute.ZoomGroupAttribute.*;

public class ZoomGroupsAdapter extends BaseAdapter<ZoomGroup> {

    @Override
    public ObjectClass getType() {
        return ObjectClass.GROUP;
    }

    @Override
    public Class<ZoomGroup> getIdentityModelClass() {
        return ZoomGroup.class;
    }

    @Override
    public List<ConnectorAttribute> getConnectorAttributes() {
        List<ConnectorAttribute> result = new ArrayList<>();
        result.add(new ConnectorAttribute(GROUP_ID.name(), STRING, NOT_UPDATEABLE));
        result.add(new ConnectorAttribute(GROUP_NAME.name(), STRING, NOT_UPDATEABLE));
        result.add(new ConnectorAttribute(TOTAL_MEMBERS.name(), INTEGER, NOT_UPDATEABLE));
        return result;
    }

    @Override
    protected ZoomGroup constructModel(Set<Attribute> attributes, boolean creation) {
        ZoomGroup group = new ZoomGroup();
        group.setId(AdapterValueTypeConverter.getIdentityIdAttributeValue(attributes));
        group.setName(AdapterValueTypeConverter.getSingleAttributeValue(String.class, attributes, GROUP_NAME));
        group.setTotalMembers(AdapterValueTypeConverter.getSingleAttributeValue(Integer.class, attributes, TOTAL_MEMBERS));
        return group;
    }

    @Override
    protected List<Attribute> constructAttributes(ZoomGroup group) {
        List<Attribute> attributes = new ArrayList<>();

        attributes.add(AttributeBuilder.build(GROUP_ID.name(), group.getId()));
        attributes.add(AttributeBuilder.build(GROUP_NAME.name(), group.getName()));
        attributes.add(AttributeBuilder.build(TOTAL_MEMBERS.name(), group.getTotalMembers()));

        return attributes;
    }
}
