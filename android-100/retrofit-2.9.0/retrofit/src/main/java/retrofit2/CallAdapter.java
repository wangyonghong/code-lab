/*
 * Copyright (C) 2015 Square, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package retrofit2;

import java.lang.annotation.Annotation;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import javax.annotation.Nullable;

/**
 * Adapts a {@link Call} with response type {@code R} into the type of {@code T}. Instances are
 * created by {@linkplain Factory a factory} which is {@linkplain
 * Retrofit.Builder#addCallAdapterFactory(Factory) installed} into the {@link Retrofit} instance.
 *
 * 将 Call<R> 类型的调用适配为 T 类型的调用。
 * 具体来说:
 * - R 是 Call 的响应类型
 * - T 是适配后的类型
 * - CallAdapter 由 Factory 工厂创建,并通过 Retrofit.Builder#addCallAdapterFactory(Factory) 方法安装到 Retrofit 中
 * 这个接口的作用是,可以将 Retrofit 的 Call 类型进行适配,转换为其他类型。比如:
 * - 将 Call<ResponseBody> 转换为 Call<String>
 * - 将 Call<List<User>> 转换为 Observable<List<User>>
 * 等等。这给予了 Retrofit 很大的灵活性,可以返回各种类型,而不仅仅是 Call。
 */
public interface CallAdapter<R, T> {
  /**
   * Returns the value type that this adapter uses when converting the HTTP response body to a Java
   * object. For example, the response type for {@code Call<Repo>} is {@code Repo}. This type is
   * used to prepare the {@code call} passed to {@code #adapt}.
   *
   * <p>Note: This is typically not the same type as the {@code returnType} provided to this call
   * adapter's factory.
   */
  Type responseType();

  /**
   * Returns an instance of {@code T} which delegates to {@code call}.
   *
   * <p>For example, given an instance for a hypothetical utility, {@code Async}, this instance
   * would return a new {@code Async<R>} which invoked {@code call} when run.
   *
   * <pre><code>
   * &#64;Override
   * public &lt;R&gt; Async&lt;R&gt; adapt(final Call&lt;R&gt; call) {
   *   return Async.create(new Callable&lt;Response&lt;R&gt;&gt;() {
   *     &#64;Override
   *     public Response&lt;R&gt; call() throws Exception {
   *       return call.execute();
   *     }
   *   });
   * }
   * </code></pre>
   */
  T adapt(Call<R> call);

  /**
   * Creates {@link CallAdapter} instances based on the return type of {@linkplain
   * Retrofit#create(Class) the service interface} methods.
   */
  abstract class Factory {
    /**
     * Returns a call adapter for interface methods that return {@code returnType}, or null if it
     * cannot be handled by this factory.
     */
    public abstract @Nullable CallAdapter<?, ?> get(
        Type returnType, Annotation[] annotations, Retrofit retrofit);

    /**
     * Extract the upper bound of the generic parameter at {@code index} from {@code type}. For
     * example, index 1 of {@code Map<String, ? extends Runnable>} returns {@code Runnable}.
     */
    protected static Type getParameterUpperBound(int index, ParameterizedType type) {
      return Utils.getParameterUpperBound(index, type);
    }

    /**
     * Extract the raw class type from {@code type}. For example, the type representing {@code
     * List<? extends Runnable>} returns {@code List.class}.
     */
    protected static Class<?> getRawType(Type type) {
      return Utils.getRawType(type);
    }
  }
}
