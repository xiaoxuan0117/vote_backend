export const validateInput = (input, type = "string") => {
  if (type === "string") {
    const validPattern = /^[\w\u4e00-\u9fa5]*$/;
    if (validPattern.test(input)) {
      return true;
    }
  } else if (type === "number") {
    if (typeof input === "number") {
      return true;
    }
  }
  return false;
};
