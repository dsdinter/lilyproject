/*
 * Copyright 2010 Outerthought bvba
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.lilyproject.repository.api;

import java.io.Closeable;
import java.io.IOException;

// IMPORTANT:
//   The Repository implementation might be wrapped to add automatic retrying of operations in case
//   of IO exceptions or when no Lily servers are available. In case this fails, a
//   RetriesExhausted(Record|Type|Blob)Exception is thrown. Therefore, all methods in this interface
//   should declare this exception. Also, the remote implementation can cause IO exceptions which are
//   dynamically wrapped in Record|Type|BlobException, thus this exception (which is a parent class
//   of the RetriesExhausted exceptions) should be in the throws clause of all methods.

/**
 * A Repository is a set of tables, tables contain records.
 *
 * <p>This interface is here for backwards compatibility with pre-2.2 Lily versions. In Lily 2.2, the functionality
 * has been split out over 2 new interfaces: {@link LRepository} and {@link LTable}, from which Repository extends.
 * <b style='color:red'>New code should be written against either {@link LRepository} or {@link LTable}.</b></p>
 *
 * <p>A Repository object represents one specific table within one specific named repository. It is obtained by:</p>
 *
 * <li>casting the result of calling on {@link RepositoryManager#getRepository(String)} or related methods
 * to Repository (this will then use the default 'record' table)</li>
 * <li>casting the result of calling {@link LRepository#getTable(String)} to Repository.</li>
 *
 * <p>For backwards compatibility, Repository extends from LTable. The methods of LTable will in this
 * case be executed against the table for which this repository has been retrieved: either the
 * default table called "record" or another table in case this Repository instance was cast from
 * a call on {@link Repository#getTable(String)}.
 *
 * <p>While Repository extends from Closeable, you don't need to call close on it. When using LilyClient,
 * Repositories are closed as part of closing LilyClient, and when embedded in the lily-server process,
 * the lifecycle is also managed automatically.</p>
 */
public interface Repository extends LTable, LRepository, Closeable {

}
