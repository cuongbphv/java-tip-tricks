splitObjectToMultipleChildren(parent: any, publicKey: string) {
    const NUMBER_OF_FIELD_OF_OBJECT = 5;
    const payload = {};
    for (let i = 1; i <= Math.ceil(Object.keys(parent).length / NUMBER_OF_FIELD_OF_OBJECT); i++) {
        const children = {};
        let j = 1;
        // tslint:disable-next-line:forin
        for (const key in parent) {
            // Một object con tối đa 10 property
            if (j === NUMBER_OF_FIELD_OF_OBJECT) {
                j = 1;
                break;
            }
            if (parent.hasOwnProperty(key)) {
                children[key] = parent[key];
                delete parent[key];
                j++;
            }
        }
        payload['param' + i] = forgeEncryptRSA(JSON.stringify(children), publicKey);
        i++;
    }
    return payload;
}