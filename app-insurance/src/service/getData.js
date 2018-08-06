import fetch from '../config/fetch'
import {wipeArray} from '../config/mUtils'
import storage from '../store/storage'

/**
 * 获取首页默认地址
 */

export const getVaildData = phoneNumber => fetch('/noAuth/sendMobileValidCode/ORDER_CONFIRM/'+phoneNumber,{},'POST');

export const submitOrder = function (code) {
  let insured = wipeArray(storage.fetch("insured"));
  let holder = wipeArray(storage.fetch("holder"));
  let insuranceOrder = wipeArray(storage.fetch("order"));
  let matters = storage.fetch("matters");
  insuranceOrder.insuranceOrderPolicyholderBank = wipeArray(insuranceOrder.insuranceOrderPolicyholderBank );
  let beneficiaries = [];
  if (!insuranceOrder.legalBeneficiary) {
    beneficiaries =storage.fetch("beneficiaries");
    beneficiaries.forEach(function (bene) {
      bene = wipeArray(bene);
    })
  }
  insuranceOrder.insuranceOrderPolicyholder = holder;
  insuranceOrder.insuranceOrderInsured = insured;
  insuranceOrder.insuranceOrderBeneficiarys = beneficiaries;
  insuranceOrder.insuranceOrderInformedMatters = matters;
  return fetch('/insurance/order/saveOrder?code='+ code, insuranceOrder,"POST");
};

export const uploadImage = function (base) {
  let base64 = {
    data:base
  };
  return fetch('/common/file/uploadBase64/PAPERWORK', base64, 'POST','fetch');
};

export const getProducts = function () {
  return fetch('/insurance/product/getInsuranceProducts', 'GET');
};

export const getOrders = function (query) {
  return fetch('/insurance/order/getOrders', query, 'POST','fetch');
};

export const getOrderDetail = function (orderCode) {
  return fetch('/insurance/order/getOrder/'+orderCode ,{}, 'POST','fetch');
};

export const getCustomerInfo = function () {
  return fetch('/customer/getCustomerInfo', {},'POST');
};

export const getProDetail = function (id) {
  return fetch("/insurance/product/getImagesByProdId/" + id);
}
