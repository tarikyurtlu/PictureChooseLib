/**
 * <pre>
 * Copyright (C) 2015  Soulwolf PictureChooseLib
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
 * </pre>
 */
package net.soulwolf.image.picturelib.rx;

/**
 * author : Soulwolf Create by 2015/7/14 15:40
 * email  : ToakerQin@gmail.com.
 */
public class CookKitchen<KITCHEN> {

    CookedCircular<? super KITCHEN> mCookedCircular;

    final ExecutorDelivery mExecutorDelivery;

    public CookKitchen(CookedCircular<? super KITCHEN> cookKitchen,ExecutorDelivery delivery){
        this.mCookedCircular = cookKitchen;
        this.mExecutorDelivery = delivery;
    }

    public void onStart(){
        if(mCookedCircular != null){
            mExecutorDelivery.execute(new PreCookRunnable(mCookedCircular));
        }
    }

    public <T extends KITCHEN> void onSuccess(T kitchen){
        if(mCookedCircular != null){
            mExecutorDelivery.execute(new CookedRunnable<KITCHEN>(mCookedCircular,kitchen));
        }
    }

    public void onError(Throwable error){
        if(mCookedCircular != null){
            mExecutorDelivery.execute(new CookErrorRunnable(mCookedCircular,error));
        }
    }

}
