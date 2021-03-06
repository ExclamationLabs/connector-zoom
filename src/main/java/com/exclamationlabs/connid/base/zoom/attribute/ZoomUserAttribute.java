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

package com.exclamationlabs.connid.base.zoom.attribute;

import org.identityconnectors.framework.common.objects.AttributeInfo;

public enum ZoomUserAttribute {
    USER_ID,
    FIRST_NAME,
    LAST_NAME,
    EMAIL,
    PASSWORD,
    LANGUAGE,
    TIME_ZONE,
    PHONE_NUMBER,
    STATUS,
    TYPE,
    CREATED_AT,
    LAST_LOGIN_TIME,
    VERIFIED,
    PERSONAL_MEETING_ID,
    GROUP_IDS;
}
