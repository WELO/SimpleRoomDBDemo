package com.example.test.simpleroomdbdemo;

import com.example.test.simpleroomdbdemo.domain.repository.DbRepository;
import com.example.test.simpleroomdbdemo.domain.repository.entity.UserInfo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.schedulers.Schedulers;
import timber.log.Timber;

public class MainActivity extends AppCompatActivity {

    private DbRepository mDbRepository;
    private int index = 0;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mDbRepository.init(this);
        mDbRepository = DbRepository.getInstance();
    }
    @OnClick({R.id.btn_get_DBTable, R.id.btn_get_one_of_DBTable, R.id.btn_add_one_to_DBTable, R.id.btn_update_one_to_DBTable
            , R.id.btn_delete_one_of_DBTable, R.id.btn_get_Count, R.id.btn_delete_all})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_get_DBTable:
                Timber.d("btn_get_DBTable ");
                mDbRepository.getOrderParentalControls()
                        .subscribeOn(Schedulers.io())
                        .subscribe(userList-> {
                                    Timber.d("btn_get_DBTable SUCCESS");
                                    Timber.d(" size= " + info.size());
                                    for (UserInfo info : orderParentalControlInfos) {
                                        showDataInfo(info);
                                    }
                                }
                                , Throwable::printStackTrace);
                break;
            case R.id.btn_get_one_of_DBTable:
                Timber.d("btn_get_one_of_DBTable ");
                mDbRepository.getOrderParentalControlInfo(0)
                        .subscribeOn(Schedulers.io())
                        .subscribe(info -> {
                            Timber.d("btn_get_one_of_DBTable SUCCESS");
                            showDataInfo(info);
                        }, Throwable::printStackTrace);
                break;
            case R.id.btn_add_one_to_DBTable:
                Timber.d("btn_add_one_to_DBTable ");
                OrderParentalControlInfo orderParentalControlInfo = OrderParentalControlInfo
                        .newBuilder().setDataId(index)
                        .setFakeAccount(FakeAccount.newBuilder().setAccountName(("account name_" + (index))).setAccountPhone("0990000_" + (index)).build())
                        .setTest(index % 2 == 0 ? EnumConverter.Test.YES : EnumConverter.Test.NO)
                        .setName("Name_" + index).setUid(index).setUserAccount("UserAccount_" + index).setUserPassword("UserPassword_" + index).build();
                mDbRepository.addOrderParentalControlInfo(orderParentalControlInfo)
                        .subscribeOn(Schedulers.io())
                        .doOnComplete(() -> Timber.d("doOnComplete btn_add_one_to_DBTable SUCCESS"))
                        .subscribe(() -> Timber.d("btn_add_one_to_DBTable SUCCESS"), Throwable::printStackTrace);
                index++;
                break;
            case R.id.btn_update_one_to_DBTable:
                OrderParentalControlInfo updateOrderParentalControlInfo = OrderParentalControlInfo
                        .newBuilder().setDataId(index - 1).setName("update")
                        .setTest((index - 1) % 2 == 0 ? EnumConverter.Test.YES : EnumConverter.Test.NO)
                        .setFakeAccount(FakeAccount.newBuilder().setAccountName(("account name_update")).setAccountPhone("0990000_" + (index - 1)).build())
                        .setUid(index - 1).setUserAccount("update").setUserPassword("update").build();
                Timber.d("btn_update_one_to_DBTable ");

                mDbRepository.updateOrderParentalControlInfo(updateOrderParentalControlInfo)
                        .subscribeOn(Schedulers.io())
                        .doOnComplete(() -> Timber.d("doOnComplete btn_update_one_to_DBTable SUCCESS"))
                        .subscribe(() -> Timber.d("btn_update_one_to_DBTable SUCCESS"), Throwable::printStackTrace);
                break;
            case R.id.btn_delete_one_of_DBTable:
                OrderParentalControlInfo deleteOrderParentalControlInfo = OrderParentalControlInfo
                        .newBuilder().setDataId(index - 1).setName("test0")
                        .setTest((index - 1) % 2 == 0 ? EnumConverter.Test.YES : EnumConverter.Test.NO)
                        .setFakeAccount(FakeAccount.newBuilder().setAccountName(("account name_" + (index - 1))).setAccountPhone("0990000_" + (index - 1)).build())
                        .setUid(index - 1).setUserAccount("test0").setUserPassword("0000").build();
                Timber.d("btn_delete_one_of_DBTable ");

                mDbRepository.deleteOrderParentalControlInfo(deleteOrderParentalControlInfo)
                        .subscribeOn(Schedulers.io())
                        .doOnComplete(() -> Timber.d("doOnComplete btn_delete_one_of_DBTable SUCCESS"))
                        .subscribe(() -> Timber.d("btn_delete_one_of_DBTable SUCCESS"), Throwable::printStackTrace);
                break;
            case R.id.btn_get_Count:
                Timber.d("btn_get_Count ");
                mDbRepository.count()
                        .subscribeOn(Schedulers.io()).subscribe(count -> Timber.d("btn_get_Count SUCCESS count=" + count), Throwable::printStackTrace);
                break;
            case R.id.btn_delete_all:
                Timber.d("btn_delete_all ");
                mDbRepository.delete()
                        .subscribeOn(Schedulers.io())
                        .subscribe(() -> Timber.d("btn_delete_all SUCCESS"), Throwable::printStackTrace);
                break;
        }
    }

    private void showDataInfo(OrderParentalControlInfo info) {
        Timber.d(" name= " + info.name + " userAccount= " + info.userAccount
                + " accountName=" + info.fakeAccount.accountName + " accountPhone= " + info.fakeAccount.accountPhone
                + " Enum=" + info.test.name);

    }
}
