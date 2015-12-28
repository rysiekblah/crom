
[![Build Status](https://travis-ci.org/rysiekblah/crom.svg?branch=master)](https://travis-ci.org/rysiekblah/crom)   [![Coverage Status](https://coveralls.io/repos/rysiekblah/crom/badge.svg?branch=master&service=github)](https://coveralls.io/github/rysiekblah/crom?branch=master)

crom
====

What is it?
-----------

Cursor Representation Object Mapping for Android

Crom is a simple library supports work with Cursors and ContentValues. Basicaly CROM transforms Cursor to POJO or POJO to ContentValue. Crom also includes CromLoader (oh yea, cool right? :) ) so you can obtain data automaticaly in backgroud.

Status
------

This library is under havy development and I'm still testing it in my applications. If you need something else, please fork CROM or let me know what do you need. Thanks!

Features
--------

| Feature                              | Method/Object       | Is supported  |
| ------------------------------------ |:-------------------:| -------------:|
| Convertion Cursor to Pojo            | cursorToPojo        |     YES       |
| Convertion Cursor to pojo list       | cursorToPojoList    |     YES       |
| Loader                               | CromLoader          |     YES       |
| Conversion pojo to ContentValue      | toContentValues     |     YES       |
| Convertion pojo list to ContentValue | toContentValuesList |     YES       |

Annotations
------------

@Column - just let CROM know that this is a column representation

@OneToMany - not implemented yet. Use it in case of joining tables. Note that only List type is supported for field with this annotation.

@Embedded - not implemented yet.

Examples:
--------
TBD soon

Licence
-------

    Copyright (C) 2014 Tomasz Kozlowski

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

         http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
