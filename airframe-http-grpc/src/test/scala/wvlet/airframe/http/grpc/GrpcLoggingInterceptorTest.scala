/*
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package wvlet.airframe.http.grpc
import wvlet.airframe.http.grpc.example.Greeter
import wvlet.airframe.http.grpc.example.Greeter.GreeterStub
import wvlet.airspec.AirSpec

/**
  */
class GrpcLoggingInterceptorTest extends AirSpec {

  protected override def design = {
    gRPC.server
      .withRouter(Greeter.router)
      .withInterceptor(new GrpcLoggingInterceptor)
      .designWithChannel
      .bind[GreeterStub].toSingleton
  }

  test("logging") { stub: GreeterStub =>
    val msg = stub.hello("gRPC")
    debug(msg)
  }
}
